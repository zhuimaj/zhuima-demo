package com.zhuima.common;

import com.lianj.commons.beans.BeanMapper;
import com.lianj.commons.beans.ResponseData;
import com.lianj.commons.constants.ErrorCodes;
import com.lianj.commons.exception.BizException;
import com.lianj.commons.utils.JSONUtils;
import com.lianj.framework.sso.util.ApiUtil;
import org.dozer.MappingException;
import org.dozer.converters.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 *
 * 功能：基础 Controller
 * 作者：xuhaowen
 * 日期：2018-03-20
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2018
 */
public abstract class BasicController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected ResponseData respSuccess(Object data) {
		return new ResponseData(data);
	}

	protected ResponseData respSuccess(Object data, String message) {
		return new ResponseData(ErrorCodes.SUCCESS, message, data);
	}

	protected ResponseData respSuccess(String message) {
		return new ResponseData(ErrorCodes.SUCCESS, message);
	}

	protected ResponseData respFail(Exception e, String message) {
		logger.error(message, e);
		Integer errorCode = ErrorCodes.FAIL;
		if (e instanceof BizException) {
			message = e.getMessage();
			Integer code = ((BizException) e).getErrorCode();
			if (code != null)
				errorCode = code;
		} else if (e instanceof RuntimeException) {
			String msg = e.getMessage();
			int dex = msg.indexOf("BizException:");
			if (msg != null && dex != -1) {
				dex += 13;
				int end = msg.indexOf("\r\n", dex);
				if (end != -1)
					message = msg.substring(dex, end);
				else
					message = msg.substring(dex);
			}
		}
		return new ResponseData(errorCode, message);
	}

	private final boolean printFlag = true;

	protected void logPrintln(Object object) {
		if (printFlag) {
			String name = getClass().getSimpleName();
			System.out.println(name + " log ：" + JSONUtils.toJson(object));
		}
	}

	protected <T> T convertParamToObj(Map<String, Object> params, Class<T> clasz) {
		try {
			return BeanMapper.map(params, clasz);
		} catch (Exception e) {
			logger.error("convertParamToObj fail, param:" + JSONUtils.toJson(params), e);
			throwConvertFail(e);
		}
		return null;
	}

	private void throwConvertFail(Exception e) {
		if (e instanceof ConversionException) {
			throw new BizException(ErrorCodes.REQUEST_PARAM_EXCEPTION, "提交数据格式有误：" + e.getCause().getMessage());
		} else if (e instanceof MappingException) {
			throw new BizException(ErrorCodes.REQUEST_PARAM_EXCEPTION, "提交数据格式有误： " + e.getMessage());
		}
		throw new BizException(ErrorCodes.REQUEST_PARAM_EXCEPTION, "您提交信息中含有不正确格式的数据");
	}

}

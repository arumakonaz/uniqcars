package kz.almaty.uniqcars.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "UNIQCARS_SERVICE_LOGGER")
@Entity(name = "uniqcars_ServiceLogger")
public class ServiceLogger extends StandardEntity {
    private static final long serialVersionUID = 5051270327747324158L;

    @Column(name = "METHOD_NAME", nullable = false)
    @NotNull
    protected String methodName;

    @Column(name = "PARAMS")
    @Lob
    protected String params;

    @Column(name = "MESSAGE", nullable = false)
    @Lob
    @NotNull
    protected String message;

    @Column(name = "STACK_TRACE")
    @Lob
    protected String stackTrace;

    @Column(name = "IS_SUCCESS", nullable = false)
    @NotNull
    protected Boolean isSuccess = false;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }
}
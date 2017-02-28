package com.itisi.itisiapp.service.net;

/**
 * 响应结果类
 * Created by Administrator on 2017/2/24.
 */
public class HttpsResult<T> {
    private int status;
    private String message;
    private int pageIndex;
    private int totalPage;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpsResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", pageIndex=" + pageIndex +
                ", totalPage=" + totalPage +
                ", data=" + data +
                '}';
    }
}

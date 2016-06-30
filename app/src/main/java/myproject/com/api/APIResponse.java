package myproject.com.api;

import java.util.List;

/**
 * Created by karthick on 29/6/16.
 */
public class APIResponse<T> {
    private int Status;
    private String Message;
    private List<T> list;
    private int Count;

    public int getStatus()
    {
        return Status;
    }

    public void setStatus(int Status)
    {
        this.Status = Status;
    }

    public String getMessage()
    {
        return Message;
    }

    public void setMessage(String Message)
    {
        this.Message = Message;
    }

    public List<T> getResult()
    {
        return list;
    }

    public void setResult(List<T> result)
    {
        this.list = result;
    }

    public int getCount()
    {
        return Count;
    }

    public void setCount(int Count)
    {
        this.Count = Count;
    }
}

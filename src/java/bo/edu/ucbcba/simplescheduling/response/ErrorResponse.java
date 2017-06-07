/*
 * ErrorResponse.java
 */

package bo.edu.ucbcba.simplescheduling.response;

import java.util.List;
import java.util.UUID;
import javax.ws.rs.core.Response;

/**
 *
 * @author Diego
 */
public class ErrorResponse {
    
    private final UUID id;
    private final Response.Status status;
    private final String code;
    private final String title;
    private final String detail;
    private final List<String> meta;

    public ErrorResponse(UUID id, Response.Status status, String code, 
            String title, String detail, List<String> meta) {
        this.id = id;
        this.status = status;
        this.code = code;
        this.title = title;
        this.detail = detail;
        this.meta = meta;
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return the status
     */
    public Response.Status getStatus() {
        return status;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @return the meta
     */
    public List<String> getMeta() {
        return meta;
    }
    
    
}

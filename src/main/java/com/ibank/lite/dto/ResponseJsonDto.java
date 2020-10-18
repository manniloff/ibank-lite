package com.ibank.lite.dto;

import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.HashSet;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseJsonDto {

    private int status;
    private String message = "Ok";
    private Collection<Object> data;

    public static ResponseJsonDto buildOk(Object object) {
        return buildOk(Sets.newHashSet(object));
    }

    public static ResponseJsonDto buildOk(Collection<Object> objects) {
        ResponseJsonDto responseJsonDto = new ResponseJsonDto();
        responseJsonDto.setStatus(HttpStatus.OK.value());
        responseJsonDto.setMessage("OK");
        responseJsonDto.setData(objects);
        return responseJsonDto;
    }

    public static ResponseJsonDto buildAccepted() {
        ResponseJsonDto responseJsonDto = new ResponseJsonDto();
        responseJsonDto.setStatus(HttpStatus.ACCEPTED.value());
        responseJsonDto.setMessage("Accepted");
        return responseJsonDto;
    }

    public static ResponseJsonDto buildNoContent() {
        ResponseJsonDto responseJsonDto = new ResponseJsonDto();
        responseJsonDto.setStatus(HttpStatus.NO_CONTENT.value());
        responseJsonDto.setMessage("No Content");
        return responseJsonDto;
    }

    public static ResponseJsonDto buildErrored(String errorMessage) {
        ResponseJsonDto responseJsonDto = new ResponseJsonDto();
        responseJsonDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseJsonDto.setMessage(errorMessage);
        responseJsonDto.setData(new HashSet<>(0));
        return responseJsonDto;
    }

    public static ResponseJsonDto buildBadRequest(String errorMessage) {
        ResponseJsonDto responseJsonDto = new ResponseJsonDto();
        responseJsonDto.setStatus(HttpStatus.BAD_REQUEST.value());
        responseJsonDto.setMessage(errorMessage);
        responseJsonDto.setData(new HashSet<>(0));
        return responseJsonDto;
    }
}

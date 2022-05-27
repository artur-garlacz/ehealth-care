package pl.agarlacz.ehealthcare.response.statics;

import java.util.Optional;

public class GetItemResponse<T> {
    private Optional<String> message;
    private T data;
}


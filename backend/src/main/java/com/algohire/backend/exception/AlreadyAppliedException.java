package com.algohire.backend.exception;

import org.apache.kafka.common.protocol.types.Field;

public class AlreadyAppliedException extends RuntimeException{
    public AlreadyAppliedException(String mes)
    {super(mes);}
}

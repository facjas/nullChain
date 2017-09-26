package org.inchain.mq.exception;

import org.inchain.exception.InchainException;

/**
 * Created by Niels on 2017/9/20.
 */
public class QueueException extends InchainException {

    public QueueException(String message) {
        super(message);
    }
    public QueueException(Throwable e) {
        super(e);
    }
}

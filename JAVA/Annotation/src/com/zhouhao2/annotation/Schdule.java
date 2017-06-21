package com.zhouhao2.annotation;
import java.lang.annotation.Repeatable;;

@Repeatable(Schdules.class)
public @interface Schdule {
    String breakfast();
    String lunch();
    String  dinner();
}

package com.qa.pojo.PODAMpojos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericPojo<F, S> {
    private F firstValue;
    private S secondValue;
}

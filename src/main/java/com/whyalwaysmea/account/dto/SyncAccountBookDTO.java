package com.whyalwaysmea.account.dto;

import com.whyalwaysmea.account.parameters.RecordParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Long
 * @Date: Create in 17:12 2018/6/29
 * @Description:
 */
@Data
public class SyncAccountBookDTO implements Serializable{

    private static final long serialVersionUID = 1992003901441657401L;

    private RecordParam recordParam;

    private String userId;
}

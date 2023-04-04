package com.example.demo.model.binancewalet;

import lombok.Data;

@Data
public class SnapshotVos {
    String type;
    String updateTime;
    DataBinance data;
}

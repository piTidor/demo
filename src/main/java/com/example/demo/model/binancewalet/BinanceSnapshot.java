package com.example.demo.model.binancewalet;

import lombok.Data;

@Data
public class BinanceSnapshot {
    String code;
    String msg;
    SnapshotVos[] snapshotVos;
}

package com.example.demo.model.binancewalet;

import lombok.Data;

@Data
public class DataBinance {
    String totalAssetOfBtc;
    Balance[] balances;
}

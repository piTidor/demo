package com.example.demo.model.binancewalet;

import lombok.Data;

@Data
public class Balance {
    String asset;
    String free;
    String locked;
}

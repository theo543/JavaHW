package com.star.storage.oop.hw5_pos;

import java.util.Date;

public record Card(Date expirationDate, String ownerName, Integer cardNumber) {
}

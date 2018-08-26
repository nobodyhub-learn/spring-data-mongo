package com.nobodyhub.learn.mongodb.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author yan_h
 */
@RequiredArgsConstructor
@ToString
@Getter
public class Person {
    private String id;
    private final String name;
    private final int age;
}

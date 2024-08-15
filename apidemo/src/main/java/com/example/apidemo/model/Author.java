package com.example.apidemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
             private int id;
             private int idBook;
             private  String firstName;
             private  String lastName;
}

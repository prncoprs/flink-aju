/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aju.demo.helloworld;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @ClassName HelloWorld
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/7/6
 */
public class HelloWorld {
    public static void main(String[] args) {
        Map<String, Object> m = new Hashtable<>();
        Student s1 = new Student(1L, "student1");
        Student s2 = new Student(2L, "student2");
        m.put("s1", s1);
        m.put("s2", s2);
        Student s1r = (Student) m.get("s1");
        Student s2r = (Student) m.get("s2");

        System.out.println(s1r);
        System.out.println(s2r);

        m.clear();
        m.put("s1", s1);
        s1r = (Student) m.get("s1");
        s2r = (Student) m.get("s2");

        System.out.println(s1r);
        System.out.println(s2r);

        System.out.println(1L<<60);
        System.out.println((1L<<60)+1L);
        System.out.println((1L<<60)+(0xFFFFFFFFFFFFFFFFL&1L));
        System.out.println((1L<<60)+(0x0FFFFFFFFFFFFFFFL&1L));
    }


}

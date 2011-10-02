/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jayway.restassured.itest.java;

import com.jayway.restassured.itest.java.support.WithJetty;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.annotation.XmlRootElement;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ObjectMappingITest extends WithJetty {

    @Test
    public void mapResponseToObjectUsingJackson() throws Exception {
        final ScalatraObject object = get("/hello").as(ScalatraObject.class);

        assertThat(object.getHello(), equalTo("Hello Scalatra"));
    }

    @Test
    public void mapResponseToObjectUsingJaxb() throws Exception {
        final Greeting object = given().parameters("firstName", "John", "lastName", "Doe").when().get("/greetXML").as(Greeting.class);

        assertThat(object.getFirstName(), equalTo("John"));
        assertThat(object.getLastName(), equalTo("Doe"));
    }

    @Ignore
    private static class ScalatraObject {
        private String hello;

        public String getHello() {
            return hello;
        }

        public void setHello(String hello) {
            this.hello = hello;
        }
    }

    @Ignore
    @XmlRootElement
    private static class Greeting {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}

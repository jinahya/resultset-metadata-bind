/*
 * Copyright 2016 Jin Kwon &lt;onacit_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.sql.resultset.metadata.bind;

import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class JaxbTest {

    static void marshalInstance(final Object jaxbElement)
            throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(
                JaxbTest.class.getPackage().getName());
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(jaxbElement, System.out);
    }

    @Test
    public void printSchema() throws JAXBException, IOException {
        final JAXBContext context
                = JAXBContext.newInstance(getClass().getPackage().getName());
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                    throws IOException {
                return new StreamResult(System.out) {
                    @Override
                    public String getSystemId() {
                        return "noid";
                    }
                };
            }
        });
    }
}
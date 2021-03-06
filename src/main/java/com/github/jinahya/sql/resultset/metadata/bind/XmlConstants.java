/*
 * Copyright 2013 <a href="mailto:onacit@gmail.com">Jin Kwon</a>.
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

/**
 * Constants for XML binding.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class XmlConstants {

    /**
     * The XML namespace URI for resultset-metadata-bind.
     */
    public static final String RESULTSET_METADATA_NS_URI
            = "http://github.com/jinahya/resultset/metadata/bind";

    static final String RESULTSET_MEATDATA_NS_PREFIX = "r";

    private XmlConstants() {
        super();
    }
}

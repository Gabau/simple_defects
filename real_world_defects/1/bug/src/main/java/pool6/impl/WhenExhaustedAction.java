/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pool6.impl;
import java.util.NoSuchElementException;

/**
 * A "when exhausted action" type indicating that when the pool is
 * exhausted (i.e., the maximum number of active objects has
 * been reached).
 *
 * @version $Revision: 1023465 $ $Date: 2010-10-17 06:37:50 -0500 (Sun, 17 Oct 2010) $
 * @since Pool 2.0
 */
public enum WhenExhaustedAction {

    /**
     * The {@code borrowObject()} method should fail, throwing a
     * {@link NoSuchElementException}.
     */
    FAIL,

    /**
     * The {@code borrowObject()} method should block until a new
     * object is available, or the {@link GenericKeyedObjectPool#getMaxWait maximum wait time}
     * has been reached.
     */
    BLOCK,

    /**
     * The {@code borrowObject()} method should simply create a
     * new object anyway.
     */
    GROW
}

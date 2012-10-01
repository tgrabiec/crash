/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.crsh.cmdline.matcher;

import java.util.HashMap;
import java.util.Map;

public class InvocationContext {

  /** . */
  private final Map<Class<?>, Object> attributes;

  public InvocationContext() {
    this.attributes = new HashMap<Class<?>, Object>();
  }

  public <T> void setAttribute(Class<T> key, T value) {
    if (key == null) {
      throw new NullPointerException();
    }
    if (value == null) {
      attributes.remove(key);
    } else {
      attributes.put(key, value);
    }
  }

  public <T> T getAttribute(Class<T> key) {
    Object value = attributes.get(key);
    return value != null ? key.cast(value) : null;
  }
}
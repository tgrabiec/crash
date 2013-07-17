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
package org.crsh.script;

import org.crsh.AbstractTestCase;

import java.io.StringReader;

/** @author Julien Viet */
public class ScriptParserTestCase extends AbstractTestCase {

  public void testVariable() throws Exception {
    assertVariable("$$");
    assertVariable("$?");
    assertVariable("$^");
    assertVariable("$a");
    failVariable("$:");
    failVariable("$:a");
    assertVariable("$a:b");
    assertVariable("@a");
    failVariable("@:");
    failVariable("@:a");
    assertVariable("@a:b");
    failVariable("");
    failVariable("a");
    failVariable("${");
    failVariable("${a");
    assertVariable("${a}");
    failVariable("${a:}");
    assertVariable("${:a}");
    assertVariable("${a:b}");
  }

  private void assertVariable(String s) {
    try {
      parser(s).variable();
    }
    catch (ParseException e) {
      throw failure(e);
    }
  }

  private void failVariable(String s) {
    try {
      parser(s).variable();
      throw failure("Was expecting " + s + " to fail");
    }
    catch (TokenMgrError e) {
      // Ok
    }
    catch (ParseException e) {
      // Ok
    }
  }

  public void testBracedVariable() throws Exception {
    failBracedVariable("");
    failBracedVariable("a");
    failBracedVariable("${");
    failBracedVariable("${a");
    assertBracedVariable("${a}");
    failBracedVariable("${a:}");
    assertBracedVariable("${:a}");
    assertBracedVariable("${a:b}");
  }

  private void assertBracedVariable(String s) {
    try {
      parser(s).braced_variable();
    }
    catch (ParseException e) {
      throw failure(e);
    }
  }

  private void failBracedVariable(String s) {
    try {
      parser(s).braced_variable();
      throw failure("Was expecting " + s + " to fail");
    }
    catch (TokenMgrError e) {
      // Ok
    }
    catch (ParseException e) {
      // Ok
    }
  }

  private ScriptParser parser(String s) {
    return new ScriptParser(new StringReader(s));
  }
}

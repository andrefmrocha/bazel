// Copyright 2010 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.analysis.config;

import static org.junit.Assert.assertEquals;

import com.google.devtools.build.lib.analysis.util.BuildViewTestCaseForJunit4;
import com.google.devtools.build.lib.packages.BuildType;
import com.google.devtools.build.lib.packages.RuleClass;
import com.google.devtools.build.lib.packages.RuleFactory;
import com.google.devtools.build.lib.packages.TargetUtils;
import com.google.devtools.build.lib.packages.TriState;
import com.google.devtools.build.lib.testutil.TestRuleClassProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for link stamping.
 */
@RunWith(JUnit4.class)
public class StampTest extends BuildViewTestCaseForJunit4 {
  /**
   * Tests that link stamping is disabled for all tests that support it.
   */
  @Test
  public void testNoStampingForTests() throws Exception {
    RuleFactory ruleFactory = new RuleFactory(TestRuleClassProvider.getRuleClassProvider());
    for (String name : ruleFactory.getRuleClassNames()) {
      RuleClass ruleClass = ruleFactory.getRuleClass(name);
      if (TargetUtils.isTestRuleName(name) && ruleClass.hasAttr("stamp", BuildType.TRISTATE)) {
        assertEquals(TriState.NO, ruleClass.getAttributeByName("stamp").getDefaultValue(null));
      }
    }
  }
}

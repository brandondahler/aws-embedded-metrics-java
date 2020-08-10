/*
 *   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package software.amazon.cloudwatchlogs.emf.model;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class MetricDefinitionTest {

    @Test(expected = NullPointerException.class)
    public void testThrowExceptionIfNameIsNull() {
        new MetricDefinition(null);
    }

    @Test
    public void testSerializeMetricDefinitionWithoutUnit() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MetricDefinition metricDefinition = new MetricDefinition("Time");
        String metricString = objectMapper.writeValueAsString(metricDefinition);

        assertEquals(metricString, "{\"Name\":\"Time\",\"Unit\":\"None\"}");
    }

    @Test
    public void testSerializeMetricDefinition() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MetricDefinition metricDefinition = new MetricDefinition("Time", Unit.MILLISECONDS);
        String metricString = objectMapper.writeValueAsString(metricDefinition);

        assertEquals(metricString, "{\"Name\":\"Time\",\"Unit\":\"Milliseconds\"}");
    }
}
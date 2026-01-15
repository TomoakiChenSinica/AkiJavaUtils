/*
 * Copyright 2026 tomoaki.
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
package tw.dev.tomoaki.util.datetime.entity.range;

import tw.dev.tomoaki.util.datetime.util.RangeHelper;

/**
 *
 * @author tomoaki
 */
public abstract class AbstractRange<T> {
    
    protected T since;
    protected T until;

    public T getSince() {
        return since;
    }

    public void setSince(T since) {
        this.since = since;
    }

    public T getUntil() {
        return until;
    }

    public void setUntil(T until) {
        this.until = until;
    }
    
    
    
    public abstract Boolean isBefore(T standard);

    public abstract Boolean isBetween(T standard);
        
    public abstract Boolean isAfter(T standard);
    
    
    @Override
    public String toString() {
        return RangeHelper.obtainClassString(this, since, until);
    }
}

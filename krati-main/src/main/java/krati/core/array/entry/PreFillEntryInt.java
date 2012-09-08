/*
 * Copyright (c) 2010-2012 LinkedIn, Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package krati.core.array.entry;

/**
 * PreFillEntryInt
 * 
 * @author jwu
 * 
 */
public class PreFillEntryInt extends PreFillEntry<EntryValueInt> {
    
    public PreFillEntryInt(int capacity) {
        super(new EntryValueIntFactory(), capacity);
    }
    
    @Override
    public void add(EntryValueInt value) {
        add(value.pos, value.val, value.scn);
    }
    
    /**
     * Adds data to this Entry.
     * 
     * @param pos
     * @param val
     * @param scn
     */
    public void add(int pos, int val, long scn) {
        if (_index < _entryCapacity) {
            _valArray.get(_index++).reinit(pos, val, scn);
            maintainScn(scn);
        } else {
            throw new EntryOverflowException();
        }
    }
}

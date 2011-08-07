/*
 * Copyright 2004-2011 H2 Group.
 * Copyright 2011 James Moger.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iciql;

/**
 * A condition contains one or two operands and a compare operation.
 * 
 * @param <A>
 *            the operand type
 */

class Condition<A> implements Token {
	CompareType compareType;
	A x, y;

	Condition(A x, A y, CompareType compareType) {
		this.compareType = compareType;
		this.x = x;
		this.y = y;
	}

	public <T> void appendSQL(SQLStatement stat, Query<T> query) {
		query.appendSQL(stat, null, x);
		stat.appendSQL(" ");
		stat.appendSQL(compareType.getString());
		if (compareType.hasRightExpression()) {
			stat.appendSQL(" ");
			query.appendSQL(stat, x, y);
		}
	}
}

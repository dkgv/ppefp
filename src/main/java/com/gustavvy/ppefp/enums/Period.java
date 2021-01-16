package com.gustavvy.ppefp.enums;

import java.time.LocalDate;

/**
 * Period.java
 *
 * @author Gustav V. Y
 */
public enum Period {
	_1D {
		@Override
		public int days() {
			return 1;
		}
	},
	_5D {
		@Override
		public int days() {
			return 5;
		}
	},
	_1MO {
		@Override
		public int days() {
			return 30;
		}
	},
	_6M {
		@Override
		public int days() {
			return 180;
		}
	},
	YTD {
		@Override
		public int days() {
			return LocalDate.now().getDayOfYear();
		}
	},
	_1Y {
		@Override
		public int days() {
			return 365;
		}
	},
	_5Y {
		@Override
		public int days() {
			return 1825;
		}
	};

	public abstract int days();

	@Override
	public String toString() {
		return name().toLowerCase().replace("_", "");
	}
}

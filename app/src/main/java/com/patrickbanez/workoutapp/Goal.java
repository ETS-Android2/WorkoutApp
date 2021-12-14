package com.patrickbanez.workoutapp;

public enum Goal {
	WEIGHT_LOSS,
	MUSCLE_BUILDING,
	STRENGTH_BUILDING,
	MAINTENANCE;

	@Override
	public String toString() {
		if (this == WEIGHT_LOSS) {
			return String.valueOf(R.string.weight_loss_text);
		} else if (this == MUSCLE_BUILDING) {
			return String.valueOf(R.string.muscle_building_text);
		} else if (this == STRENGTH_BUILDING) {
			return String.valueOf(R.string.strength_building_text);
		} else {
			return String.valueOf(R.string.maintenance_text);
		}
	}
}

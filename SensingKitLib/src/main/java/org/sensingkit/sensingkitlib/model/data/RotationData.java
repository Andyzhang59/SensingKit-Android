/*
 * Copyright (c) 2014. Queen Mary University of London
 * Kleomenis Katevas, k.katevas@qmul.ac.uk
 *
 * This file is part of SensingKit-Android library.
 * For more information, please visit http://www.sensingkit.org
 *
 * SensingKit-Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SensingKit-Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SensingKit-Android.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.sensingkit.sensingkitlib.model.data;

public class RotationData extends AbstractData {

    @SuppressWarnings("unused")
    private static final String TAG = "GyroscopeData";

    protected float x;
    protected float y;
    protected float z;
    protected float cos;
    protected float headingAccuracy;

    public RotationData(long timestamp, float x, float y, float z, float cos, float headingAccuracy) {

        super(timestamp);

        this.x = x;
        this.y = y;
        this.z = z;
        this.cos = cos;
        this.headingAccuracy = headingAccuracy;
    }

    public String getDataInString() {
        return String.format("%d,%f,%f,%f,%f,%f", this.timestamp, this.x, this.y, this.z, this.cos, this.headingAccuracy);
    }

    @SuppressWarnings("unused")
    public float getX() {
        return this.x;
    }

    @SuppressWarnings("unused")
     public float getY() {
        return this.y;
    }

    @SuppressWarnings("unused")
    public float getZ() {
        return this.z;
    }

    @SuppressWarnings("unused")
    public float getCos() {
        return this.cos;
    }

    @SuppressWarnings("unused")
    public float getHeadingAccuracy() {
        return this.headingAccuracy;
    }

}

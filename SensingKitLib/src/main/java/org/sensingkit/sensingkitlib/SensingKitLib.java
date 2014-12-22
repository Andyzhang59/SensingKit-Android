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

package org.sensingkit.sensingkitlib;

import android.content.Context;
import android.os.PowerManager;

import org.sensingkit.sensingkitlib.modules.SensorModuleType;
import org.sensingkit.sensingkitlib.modules.SensorModuleManager;

public class SensingKitLib implements SensingKitLibInterface {

    @SuppressWarnings("unused")
    private static final String TAG = "SensingKitLib";

    private static SensingKitLib sSensingKitLib;

    private final Context mApplicationContext;
    private PowerManager.WakeLock mWakeLock;

    private SensorModuleManager mSensorModuleManager;

    @SuppressWarnings("unused")
    public static SensingKitLibInterface getSensingKitLib(final Context context) throws SKException {
        if (context == null) {
            throw new SKException(TAG, "Context cannot be null", SKExceptionErrorCode.UNKNOWN_ERROR);
        }

        if (sSensingKitLib == null) {
            sSensingKitLib = new SensingKitLib(context);
        }

        return sSensingKitLib;
    }

    private SensingKitLib(final Context context) throws SKException {
        mApplicationContext = context;
        mSensorModuleManager = SensorModuleManager.getSensorManager(context);
    }

    public void getDataFromSensor(SensorModuleType moduleType) throws SKException {
        mSensorModuleManager.getDataFromSensor(moduleType);
    }

    public void subscribeToSensor(SensorModuleType moduleType, SKSensorDataListener dataListener) throws SKException {
        mSensorModuleManager.subscribeToSensor(moduleType, dataListener);
    }

    public void unsubscribeFromSensor(SensorModuleType moduleType, SKSensorDataListener dataListener) throws SKException {
        mSensorModuleManager.unsubscribeFromSensor(moduleType, dataListener);
    }

    public void unsubscribeAllFromSensor(SensorModuleType moduleType) throws SKException {
        mSensorModuleManager.unsubscribeAllFromSensor(moduleType);
    }

    public void startContinuousSensingWithSensor(SensorModuleType moduleType) throws SKException {
        mSensorModuleManager.startContinuousSensingWithSensor(moduleType);
    }

    public void stopContinuousSensingWithSensor(SensorModuleType moduleType) throws SKException {
        mSensorModuleManager.stopContinuousSensingWithSensor(moduleType);
    }

    public void pauseContinuousSensingWithSensor(SensorModuleType moduleType) throws SKException {
        mSensorModuleManager.pauseContinuousSensingWithSensor(moduleType);
    }

    public void unpauseContinuousSensingWithSensor(SensorModuleType moduleType) throws SKException {
        mSensorModuleManager.unpauseContinuousSensingWithSensor(moduleType);
    }

    //region Wake Lock methods

    private void acquireWakeLock() {
        if ((mWakeLock == null) || (!mWakeLock.isHeld())) {
            PowerManager pm = (PowerManager) mApplicationContext.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "WakeLock");
            mWakeLock.acquire();
        }
    }

    private void releaseWakeLock() {
        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
        }
    }

    private boolean checkWakeLockPermission() throws SKException {
        return SKUtilities.checkPermission(
                mApplicationContext,
                "android.permission.WAKE_LOCK");
    }

    //endregion
}
package com.fisincorporated.soaringforecast.dagger;

import com.fisincorporated.soaringforecast.about.AboutActivity;
import com.fisincorporated.soaringforecast.about.AboutFragment;
import com.fisincorporated.soaringforecast.airport.AirportActivity;
import com.fisincorporated.soaringforecast.airport.airportweather.AirportMetarTafFragment;
import com.fisincorporated.soaringforecast.airport.list.AirportListFragment;
import com.fisincorporated.soaringforecast.drawer.WeatherDrawerActivity;
import com.fisincorporated.soaringforecast.satellite.SatelliteActivity;
import com.fisincorporated.soaringforecast.satellite.noaa.NoaaSatelliteImageFragment;
import com.fisincorporated.soaringforecast.settings.SettingsPreferenceFragment;
import com.fisincorporated.soaringforecast.soaring.forecast.SoaringForecastFragment;
import com.fisincorporated.soaringforecast.task.TaskActivity;
import com.fisincorporated.soaringforecast.task.list.TaskListFragment;
import com.fisincorporated.soaringforecast.task.search.TurnpointSearchFragment;
import com.fisincorporated.soaringforecast.task.turnpoints.download.TurnpointsDownloadFragment;
import com.fisincorporated.soaringforecast.task.turnpoints.seeyou.SeeYouImportFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {})
public abstract class UIBuildersModule {

    @ContributesAndroidInjector(modules = {})
    abstract WeatherDrawerActivity bindWeatherDrawerActivity();

    @ContributesAndroidInjector(modules = {SoaringForecastModule.class})
    abstract SoaringForecastFragment bindSoaringForecastFragment();

    // --- Weather Metar/Taf -------------------
    @ContributesAndroidInjector(modules = {})
    abstract AirportActivity bindAirportActivity();

    @ContributesAndroidInjector(modules = {})
    abstract AirportMetarTafFragment bindAirportMetarTafFragment();

    @ContributesAndroidInjector(modules = {})
    abstract AirportListFragment bindAirportListFragment();


    // ---- Task/Turnpoints ----
    @ContributesAndroidInjector(modules = {})
    abstract TaskActivity bindTaskActivity();

    @ContributesAndroidInjector(modules = {})
    abstract TaskListFragment bindTaskListFragment();

    @ContributesAndroidInjector(modules = {})
    abstract TurnpointSearchFragment bindTurnpointSearchFragment();

    @ContributesAndroidInjector(modules = {})
    abstract SeeYouImportFragment bindSeeYouImportFragment();

    @ContributesAndroidInjector(modules = {})
    abstract TurnpointsDownloadFragment bindTurnpointsImportFragment();


    // ---- Satellite ----
    @ContributesAndroidInjector(modules = {})
    abstract SatelliteActivity bindSatelliteActivity();

    @ContributesAndroidInjector(modules = {})
    abstract NoaaSatelliteImageFragment bindNoaaSatelliteImageFragment();

    // ----- Settings ------------
    @ContributesAndroidInjector(modules = {})
    abstract SettingsPreferenceFragment bindSettingsPreferenceFragment();


    // ----- About ------------
    @ContributesAndroidInjector(modules = {})
    abstract AboutActivity bindAboutActivity();

    @ContributesAndroidInjector(modules = {})
    abstract AboutFragment bindAboutFragment();




}



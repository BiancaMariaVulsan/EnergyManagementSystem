// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  personApiUrl: 'http://users-ms-bv.b7gcdzh6bmd0hgee.switzerlandwest.azurecontainer.io:8080',
  deviceApiUrl: 'http://devices-ms-bv.e3eabjgxgchfaca5.switzerlandwest.azurecontainer.io:8000',
  measurementsApiUrl: 'http://monitoring-ms-bv.bsh2aqg8chcxeves.switzerlandwest.azurecontainer.io:8001/'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.

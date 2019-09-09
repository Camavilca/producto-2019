/*!
@fullcalendar/moment v4.0.1
Docs & License: https://fullcalendar.io/
(c) 2019 Adam Shaw
*/
!function(e,t){"object"==typeof exports&&"undefined"!=typeof module?t(exports,require("moment"),require("@fullcalendar/core")):"function"==typeof define&&define.amd?define(["exports","moment","@fullcalendar/core"],t):(e=e||self,t(e.FullCalendarMoment={},e.moment,e.FullCalendar))}(this,function(e,t,n){"use strict";function r(e,t){if(!(t instanceof n.Calendar))throw new Error("must supply a Calendar instance");return u(e,t.dateEnv.timeZone,null,t.dateEnv.locale.codes[0])}function a(e){return t.duration(e)}function o(e,t){var n=i(e);if(t.end){var r=u(t.start.array,t.timeZone,t.start.timeZoneOffset,t.localeCodes[0]),a=u(t.end.array,t.timeZone,t.end.timeZoneOffset,t.localeCodes[0]);return d(n,l(r),l(a),t.separator)}return u(t.date.array,t.timeZone,t.date.timeZoneOffset,t.localeCodes[0]).format(n.whole)}function l(e){return function(t){return t?e.format(t):""}}function u(e,n,r,a){var o;return"local"===n?o=t(e):"UTC"===n?o=t.utc(e):t.tz?o=t.tz(e,n):(o=t.utc(e),null!=r&&o.utcOffset(r)),o.locale(a),o}function i(e){var t=e.match(/^(.*?)\{(.*)\}(.*)$/);if(t){var n=i(t[2]);return{head:t[1],middle:n,tail:t[3],whole:t[1]+n.whole+t[3]}}return{head:null,middle:null,tail:null,whole:e}}function d(e,t,n,r){if(e.middle){var a=t(e.head),o=d(e.middle,t,n,r),l=t(e.tail),u=n(e.head),i=d(e.middle,t,n,r),f=n(e.tail);if(a===u&&l===f)return a+(o===i?o:o+r+i)+l}return t(e.whole)+r+n(e.whole)}t=t&&t.hasOwnProperty("default")?t.default:t;var f=n.createPlugin({cmdFormatter:o});e.toMoment=r,e.toDuration=a,e.default=f,Object.defineProperty(e,"__esModule",{value:!0})});
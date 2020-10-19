"use strict";

var gulp = require('gulp'),
    rimraf = require("rimraf"),
    concat = require("gulp-concat"),
    cssmin = require("gulp-cssmin"),
    uglify = require("gulp-uglify");
var merge = require('merge-stream');

//var paths = {
//    webroot: "./wwwroot/"
//};

var depscss = {
    "bootstrap": {
        "dist/css/bootstrap.min.css": ""
    },
    "chart.js": {
        "dist/Chart.css": "",
        "dist/Chart.min.css": "",
    },
    "lightbox2": {
        "dist/**/*": "/lightbox2"
        // "dist/images/close.png": "/lightbox2/images",
        // "dist/images/loading.gif": "/lightbox2/images",
        // "dist/images/next.png": "/lightbox2/images",
        // "dist/images/prev.png": "/lightbox2/images",
        // "dist/css/lightbox.css": "/lightbox2/css",
        // "dist/css/lightbox.min.css": "/lightbox2/css",
        // "dist/js/lightbox.js": "/lightbox2/js",
        // "dist/js/lightbox.min.js": "/lightbox2/js",
        // "dist/js/lightbox.min.map": "/lightbox2/js",
        // "dist/js/lightbox-plus-jquery.js": "/lightbox2/js",
        // "dist/js/lightbox-plus-jquery.min.js": "/lightbox2/js",
        // "dist/js/lightbox-plus-jquery.min.map": "/lightbox2/js",
    }
};

var depsjs = {
    "bootstrap": {
        "dist/js/bootstrap.min.js": ""
    },
    "chart.js": {
        "dist/Chart.js": "",
        "dist/Chart.min.js": ""
    },
    "jquery": {
        "dist/jquery.js": ""
    },
    "vue": {
        "dist/vue.min.js": "vue/"
    },
    "vue-router": {
        "dist/vue-router.min.js": "vue/"
    },
    "axios": {
        "dist/axios.min.js": ""
    }
};

gulp.task("clean", function (cb) {
    return rimraf("src/main/resources/static/lib", cb);
});

gulp.task("js", function () {
    var streams = [];

    for (var prop in depsjs) {
        console.log("Hello gulp depsjs: " + prop);
        for (var itemProp in depsjs[prop]) {
            streams.push(gulp.src("node_modules/" + prop + "/" + itemProp)
                .pipe(gulp.dest("src/main/resources/static/lib/js/" + depsjs[prop][itemProp])));
        }
    }
    return merge(streams);
});

gulp.task("css", function () {
    var streams = [];

    for (var prop in depscss) {
        console.log("Hello gulp depscss: " + prop);
        for (var itemProp in depscss[prop]) {
            streams.push(gulp.src("node_modules/" + prop + "/" + itemProp)
                .pipe(gulp.dest("src/main/resources/static/lib/css" + depscss[prop][itemProp])));
        }
    }
    return merge(streams);
});

gulp.task("scripts", gulp.series(["js", "css"]));
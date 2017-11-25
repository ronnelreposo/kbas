package controllers.printing;

import functional.core.Tuple;

interface StringDrawer<T> { T draw (Tuple<T, Tuple<String, IPoint>> t); }
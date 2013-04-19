# Unit-Load

Pallet infrastructure for a Clojure website

## Overview

The goal of this is to provide the Clojure equivalent of a LAMP server
provisioned with basic hooks for monitoring, deploying, both in
production and in test.

Software provided in this stack:

 * Nginx
 * Postgresql 9.2
 * Java

At the moment it's mostly acting as a test bed to play around with
Pallet, but the end goal is to be basic open infrastructure.

## Usage

```
$ lein pallet up --phases install,configure
```

## License

Copyright © 2013 Charles L.G. Comstock

Distributed under the Eclipse Public License, the same as Clojure.

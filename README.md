# Unit-Load

Pallet infrastructure for a Clojure Webapp

## Overview

The goal of this is to provide the Clojure equivalent of a LAMP server
provisioned with basic hooks for monitoring, deploying, both in
production and in test.

Software provided in this stack thus far:

 * Nginx
 * Postgresql 9.2
 * Java

At the moment it's mostly acting as a test bed to play around with
Pallet, but the end goal is to be basic open infrastructure.

## Usage

Install Virtualbox and Leiningen

```
$ vboxwebsrv -t0 # in a separate shell
$ lein pallet add-vmfest-image \
        https://s3.amazonaws.com/vmfest-images/debian-6.0.2.1-64bit-v0.3.vdi.gz
$ lein pallet up --phases install,configure
```

## License

Copyright © 2013 Charles L.G. Comstock

Distributed under the Eclipse Public License, the same as Clojure.

## SIS - GeoToolkit 

Also see [issue #42: Upgrade apache SIS from 2.0 to ... 1.1](https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/backend-springboot/-/issues/42)

### Architecture
GeoToolkit sits on top of SIS, and add some features that SIS is currently missing.

Apache SIS is an official Apache project, whereas Geotoolkit is an independant fork of Geotools.

Both are implementing **geoApi** interfaces, which defines iso19115.

Both are maintained mostly by Geomatys and Martin Desruisseaux.

Among those features, we need **geotk** for :
- implementation of iso19115 **temporal** classes (a `Period` implementation that matches our `TemporalExtent`)
- parsing of `GeoJson` format ( used to calculate _bbox_ of our `SpatialExtent` )
- Possibly parsing of `geotiff` in the future.

### Versions
As of march 2021, we are using versions
- _Apache SIS 2.0-M0070_ ( defined in **apiclient-geonetwork** module )
- _GeoToolkit 5.0.0-MC0082_ ( defined in **etl-mongo-geonetwork** module )

These are beta releases. They are **not** on the official maven repo, but are to be found on **Geomatys** own repo.

### Why using betas ?
We need features from both libs.
The last stable **geotk** release is 4.0.5, which require **SIS 0.8** , which is way too old.

Therefore we must use a **geotk 5.X** beta version, and it requires an **SIS 2.X** beta version.

### How/When can we move towards stable versions ?

In theory, we could use a **SIS 1.X** stable, but there is no **geotk** supporting those.


When **SIS** will support **temporal** classes, and possibly geoJson, then it will be time to think of a move to get rid of geotk, or upgrade the stack. This will probably happen with a **SIS v1.X**.

According to Martin Desruisseaux, it is no big deal to move from SIS 2.X to SIS 1.X . At least it is easier than **0.8 -> 1.X**


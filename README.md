# kolishun 

### Unusuable right now, development in progress.

`kolishun` is an extremely minimalist library meant to be used as a lightweight AABB collision engine for 2D games. It attempts to 
do no collision resolution (like slides or blocking) -- it just reports every collision that happens, and leaves it to you to 
resolve it as per your use case. The main purpose is to provide a fast collision detection algorithm in a world. `kolishun` is
inspired by the famous `bump` library (and its JVM incarnation `jbump`), but is much more simpler.

### TODO -- Write details about usage
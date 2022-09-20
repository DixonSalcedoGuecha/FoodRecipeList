# FoodRecipeList

Caracateristicas

1. Este proyecto se esta realizando con un patron de arquitectura llamado MVVM
2. Para inyeccion de dependencias se esta usando Hilt
3. para manejo de datos se esta usando Room
4. Para consumir el Api gratuita se esta usando Retrofit

Aspectos antes de ejecutar el proyecto

1. La pagina de donde se esta consumiendo esta Api gratuita se llama "https://spoonacular.com/food-api"
2. Debe registrarse para obtener  un tpoken de acceso
3. si desea cambiar el TOKEN DE ACCESO esta en el archivo Constans de la carpeta domain
4. Si la pagina esta caida o no esta registrado el app no te mostrara la lista de recetas
5. Puedes agregar cada receta a tus favoritos
6. puedes listar tus favoritos dando clikc en el corazon de la parte superior de la pantalla principal
7. puedes biscar tus recetas favoritas tecleando el nombre de la receta en el icono de buscar

Problemas al momento de realizar el desarrollo

1. El Api es muy insetable y muchas veces no te va a retornar nada por que esta caida
2. se tubieron que hacer varias validaciones al momento de agregar una receta a tus favoritos

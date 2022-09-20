# FoodRecipeList

Características

1. Este proyecto se está realizando con un patrón de arquitectura llamado MVVM
2. Para inyección de dependencias se está usando Hilt
3. Para manejo de datos se está usando Room
4. Para consumir el API gratuita se está usando Retrofit

Aspectos antes de ejecutar el proyecto

1. La página de donde se está consumiendo esta API gratuita se llama "https://spoonacular.com/food-api"
2. Debe registrarse para obtener un toquen de acceso
3. Si desea cambiar el TOKEN DE ACCESO está en el archivo Constans de la carpeta domain
4. Si la página esta caída o no está registrado el app no te mostrara la lista de recetas
5. Puedes agregar cada receta a tus favoritos
6. Puedes listar tus favoritos dando clic en el corazón de la parte superior de la pantalla principal
7. Puedes buscar tus recetas favoritas tecleando el nombre de la receta en el icono de buscar

Problemas al momento de realizar el desarrollo

1. El Api es muy insetable y muchas veces no te va a retornar nada por que esta caida
2. se tubieron que hacer varias validaciones al momento de agregar una receta a tus favoritos
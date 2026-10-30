
## Paso 0 — Verificar que Git tiene tus datos

El repositorio local ya quedó inicializado y configurado con tus datos, así que normalmente no hay
que hacer nada. Para confirmarlo:

```bash
cd proyecto_carfactory
git config user.name
git config user.email
```

Debe responder `Elieth Sanchez` y `esanchez0395@gmail.com`. Si estuviera vacío:

```bash
git config --global user.name "Elieth Sanchez"
git config --global user.email "esanchez0395@gmail.com"
```

---

## Paso 1 — Crear el repositorio en GitHub

1. Entrá a <https://github.com/new> con la cuenta **Gali395**.
2. **Repository name:** `carfactory-fide`
3. **Description:** `Proyecto SC-304 Estructuras de Datos — CarFactory-Fide`
4. **Visibility:** **Private** (el enunciado lo exige).
5. **No marques** "Add a README file", ni `.gitignore`, ni licencia: el proyecto ya los trae y
   crearlos en GitHub provoca conflictos al subir.
6. Clic en **Create repository**.

---

## Paso 2 — Conectar y subir el código

GitHub te mostrará la URL del repositorio. En la terminal, desde la carpeta del proyecto:

```bash
cd proyecto_carfactory
git remote add origin https://github.com/Gali395/carfactory-fide.git
git push -u origin main
```

Si te pide credenciales, usá tu usuario `Gali395` y un **token de acceso personal** en lugar de la
contraseña (GitHub ya no acepta contraseña por HTTPS):

1. Entrá a <https://github.com/settings/tokens> → **Generate new token (classic)**.
2. Nombre: `SC-304`, expiración: 30 días, permiso marcado: **repo**.
3. Copiá el token y pegalo cuando la terminal pida la contraseña. **Guardalo**, no se vuelve a mostrar.

Alternativa más cómoda: instalar GitHub CLI y autenticarte con `gh auth login`.

---

## Paso 3 — Agregar al profesor como colaborador

1. En el repositorio, entrá a **Settings** → **Collaborators** (o *Access* en la versión nueva).
2. Clic en **Add people**.
3. Escribí el usuario **`pgomez-ufide`** y confirmá con el permiso **Write**.
4. GitHub le enviará una invitación que él debe aceptar.

Sin este paso el profesor no puede revisar el repositorio y la nota se ve afectada.

---

## Paso 4 — Verificar que el repositorio quedó bien

Abrí `https://github.com/Gali395/carfactory-fide` y comprobá:

- Aparece la carpeta `src` con `estructuras` y `pruebas`, la carpeta `docs` y el `README.md`.
- **No** aparece ninguna carpeta `bin`, ni archivos `.class` (eso lo controla el `.gitignore`).
- El repositorio dice **Private** junto al nombre.

---

## Paso 5 — Repartir el trabajo entre los cinco integrantes

La rúbrica exige **commits significativos de todos los integrantes**. La forma correcta y honesta es
que cada uno aporte código real de su módulo, no inventar commits.

**Cada integrante, una sola vez:**

```bash
git clone https://github.com/Gali395/carfactory-fide.git
cd carfactory-fide
git config user.name "Nombre Apellido"
git config user.email "correo@del.integrante"
```

**Flujo para cada tarea (repetir por funcionalidad):**

```bash
git pull origin main                      # traer lo último
git checkout -b feature/cinta             # rama nueva para la funcionalidad
# ... trabajar en el código ...
git add .
git commit -m "feat(cinta): agrega reabastecimiento aleatorio"
git push -u origin feature/cinta
```

Después, en GitHub, se abre un **Pull Request** de `feature/cinta` hacia `main` y **otro integrante**
lo revisa y aprueba antes de fusionarlo. Eso es exactamente lo que el profesor verá como
participación equitativa.

**Reparto sugerido según el plan de trabajo:**

| Integrante | Ramas de trabajo |
|---|---|
| Emiliano Jiménez Cedeño | `feature/estructuras`, `feature/cinta` |
| Mariana Rojas Villegas | `feature/modelo`, `feature/construccion` |
| Richard Tellez Ramirez | `feature/ui`, `feature/hub` |
| Elieth Moreno Sánchez | `feature/fabricas`, `feature/ascensos`, `docs/avance1` |
| Michael Quirós Gómez | `feature/basurero`, `feature/secciones` |

---

## Paso 6 — Convención de mensajes de commit

Formato `tipo(alcance): descripción`, en presente y en español:

| Tipo | Cuándo usarlo | Ejemplo |
|---|---|---|
| `feat` | funcionalidad nueva | `feat(construccion): valida materiales de la receta` |
| `fix` | corrección de un error | `fix(cinta): limpia la referencia al fin al desencolar` |
| `docs` | documentación | `docs(avance1): agrega documento de primer avance` |
| `refactor` | reordenar sin cambiar comportamiento | `refactor(estructuras): extrae NodoLista a su archivo` |
| `test` | pruebas | `test(estructuras): agrega 38 verificaciones` |

Alcances del proyecto: `estructuras`, `modelo`, `construccion`, `cinta`, `basurero`, `ui`, `fabricas`,
`ascensos`, `docs`.

---

## Paso 7 — Proteger la rama principal (recomendado)

1. **Settings** → **Branches** → **Add branch protection rule**.
2. Branch name pattern: `main`.
3. Marcá **Require a pull request before merging**.

Con esto el profesor ve que el equipo trabajó con revisión cruzada y no con commits directos.

---

## Estado actual del repositorio local

Ya está todo hecho excepto los pasos que requieren tu cuenta de GitHub:

- Repositorio Git inicializado en la rama `main`.
- Autor configurado como `Elieth Sanchez <esanchez0395@gmail.com>`.
- `.gitignore` que excluye `bin/`, `*.class` y archivos de IDE.
- Primer commit con las estructuras propias, las pruebas y la documentación del avance.

Para ver lo que quedó registrado:

```bash
git log --oneline
git ls-files
```

---

## Errores frecuentes y cómo resolverlos

**`remote origin already exists`**
```bash
git remote set-url origin https://github.com/Gali395/carfactory-fide.git
```

**`failed to push some refs` porque el repo remoto tiene un README**
```bash
git pull --rebase origin main
git push -u origin main
```

**`Authentication failed`** → estás usando la contraseña en lugar del token. Generá el token del
Paso 2 y usalo como contraseña.

**Aparecen archivos `.class` en el repositorio** → el `.gitignore` no se aplicó porque los archivos
ya estaban rastreados:
```bash
git rm -r --cached bin
git commit -m "chore: excluye archivos compilados del repositorio"
```


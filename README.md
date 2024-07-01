# EnviroWaste Application

EnviroWaste is a Spring Boot application that exposes a REST API for clients. As suggested by the name, this application is a waste sorting application that aims to promote sustainable waste management practices. The features of the application include waste category lookup where users receive information on different types of waste categories, disposal guideline retrieval which provides insight on how to correctly dispose of waste and recycling tips to ensure that users are sufficiently equipped to have a positive on impact on the natural environment around them.
## Setup and Running the Project

### Prerequisites

- Java 11 or higher
- Maven

### Running the Application

1. **Clone the repository:**

    ```bash
    git clone https://github.com/KJ-Tsatsi/EnviroWaste.git
    cd waste-sorting-app-backend
    ```

2. **Build the application:**

    ```bash
    mvn clean install
    ```

3. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```


## REST Endpoints

### Waste Categories

#### Get all waste categories

- **Endpoint:** `GET /api/v1/waste`
- **Description:** Retrieve a list of all waste categories.
- **Response:**
  ```json
  [
    {
    "id": 1,
    "category": "Agricultural"
    },
    {
    "id": 2,
    "category": "Chemical"
    }
  ]
  ```

#### Get a waste category by ID

- **Endpoint:** `GET /api/v1/waste/{categoryId}`
- **Description:** Retrieve a specific waste category by its ID.
- **Response:**
  ```json
  {
    "id": 1,
    "category": "Agricultural"
    }
  ```

#### Add a new waste category

- **Endpoint:** `POST /api/v1/waste`
- **Description:** Add a new waste category.
- **Request Body:**
  ```json
  {
    "category": "Hazardous"
  }
  ```

#### Delete a waste category

- **Endpoint:** `DELETE /api/v1/waste/{categoryId}`
- **Description:** Delete a specific waste category by its ID.

#### Update a waste category

- **Endpoint:** `PUT /api/v1/waste/{categoryId}`
- **Description:** Update a specific waste category by its ID.
- **Request Body:**
  ```json
  {
    "category": "Updated Category"
  }
  ```

### Disposal Guidelines

#### Get all disposal guidelines

- **Endpoint:** `GET /api/v1/waste/guidelines`
- **Description:** Retrieve a list of all disposal guidelines.
- **Response:**
  ```json
  [
    {
      "id": 1,
      "guideline": "Autoclaving: Sterilize infectious waste like sharps and cultures using autoclaves before disposal to render them non-infectious.",
      "wasteCategory": "Medical"
   },
   {
    "id": 2,
    "guideline": "Landfill disposal: Dispose of non-recyclable demolition waste in permitted landfills that accept construction and demolition debris.",
    "wasteCategory": "Demolition"
  }
  ]
  ```

#### Get a disposal guideline by ID

- **Endpoint:** `GET /api/v1/waste/guidelines/id/{guidelineId}`
- **Description:** Retrieve a specific disposal guideline by its ID.
- **Response:**
  ```json
  {
    "id": 1,
    "guideline": "Rinse plastic bottles before recycling.",
    "wasteCategory": "Plastic"
  }
  ```

#### Get disposal guidelines by category

- **Endpoint:** `GET /api/v1/waste/guidelines/category/{category}`
- **Description:** Retrieve disposal guidelines by category.
- **Response:**
  ```json
  [
    {
      "id": 6,
      "guideline": "Hazardous materials handling: Remove and dispose of hazardous materials such as asbestos and lead-based paint according to specific regulations and guidelines.",
      "wasteCategory": "Demolition"
    },
   {
      "id": 7,
      "guideline": "Landfill disposal: Dispose of non-recyclable demolition waste in permitted landfills that accept construction and demolition debris.",
      "wasteCategory": "Demolition"
    }
  ]
  ```

#### Add a new disposal guideline

- **Endpoint:** `POST /api/v1/waste/guidelines`
- **Description:** Add a new disposal guideline.
- **Request Body:**
  ```json
  {
    "guideline": "Remove lids from glass bottles before recycling.",
    "wasteCategory": "Glass"
  }
  ```

#### Delete a disposal guideline

- **Endpoint:** `DELETE /api/v1/waste/guidelines/{guidelineId}`
- **Description:** Delete a specific disposal guideline by its ID.

#### Update a disposal guideline

- **Endpoint:** `PUT /api/v1/waste/guidelines/{guidelineId}`
- **Description:** Update a specific disposal guideline by its ID.
- **Request Body:**
  ```json
  {
    "guideline": "Updated guideline.",
    "wasteCategory": "Plastic"
  }
  ```

### Recycling Tips

#### Get all recycling tips

- **Endpoint:** `GET /api/v1/waste/tip`
- **Description:** Retrieve a list of all recycling tips.
- **Response:**
  ```json
  [
    {
      "id": 1,
      "tip": "Know Your Local Recycling Rules: Different municipalities have varying guidelines for what can and cannot be recycled."
    }
  ]
  ```

#### Get a recycling tip by ID

- **Endpoint:** `GET /api/v1/waste/tip/{tipId}`
- **Description:** Retrieve a specific recycling tip by its ID.
- **Response:**
  ```json
  {
    "id": 1,
    "tip": "Know Your Local Recycling Rules: Different municipalities have varying guidelines for what can and cannot be recycled."
  }
  ```

#### Add a new recycling tip

- **Endpoint:** `POST /api/v1/waste/tip`
- **Description:** Add a new recycling tip.
- **Request Body:**
  ```json
  {
    "tip": "Clean and Dry Items: Rinse food and drink containers to remove any residue before placing them in the recycling bin."
  }
  ```

#### Delete a recycling tip

- **Endpoint:** `DELETE /api/v1/waste/tip/{tipId}`
- **Description:** Delete a specific recycling tip by its ID.

#### Update a recycling tip

- **Endpoint:** `PUT /api/v1/waste/tip/{tipId}`
- **Description:** Update a specific recycling tip by its ID.
- **Request Body:**
  ```json
  {
    "tip": "Updated recycling tip."
  }
  ```

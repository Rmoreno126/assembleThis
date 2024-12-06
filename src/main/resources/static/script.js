/* script.js */

document.addEventListener('DOMContentLoaded', function() {
    fetchUsers();
    fetchGames();
    fetchStoreItems();
    fetchEvents();

    // Add event listener for the Create Business Profile form
    const createBusinessForm = document.getElementById('create-business-form');
    createBusinessForm.addEventListener('submit', function(event) {
        event.preventDefault();
        createBusinessProfile();
    });
});

function fetchStoreItems() {
    fetch('/api/store')
        .then(response => response.json())
        .then(data => {
            const storeContainer = document.querySelector('.game-store');
            storeContainer.innerHTML = data.map(item => `
                <div class="game-list">
                    <img src="${item.imageUrl}" alt="${item.name}" style="width:100px; height: 100px;"/>
                    <h3>${item.name}</h3>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error fetching store items:', error));
}

function fetchEvents() {
    fetch('/api/events')
        .then(response => response.json())
        .then(data => {
            const eventsContainer = document.querySelector('.event-hero');
            eventsContainer.innerHTML = data.map(event => `
                <div class="event-list">
                    <img src="${event.imageUrl}" alt="${event.name}" style="width:100px; height: 100px;"/>
                    <h3>${event.title}</h3>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error fetching events:', error));
}

function fetchGames() {
    fetch('/api/games')
        .then(response => response.json())
        .then(data => {
            const gamesContainer = document.querySelector('.games');
            gamesContainer.innerHTML = data.map(game => `
                <div class="game-list">
                    <img src="${game.imageUrl}" alt="${game.name}" style="width:100px; height: 100px;"/>
                    <h3>${game.name}</h3>
                    <button onclick="deleteGame(${game.id})">Delete</button>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error fetching games:', error));
}

function addGame() {
    const name = document.getElementById('gameName').value;
    const type = document.getElementById('gameType').value;
    const imageUrl = document.getElementById('gameImageUrl').value;
    if (!name || !type || !imageUrl) {
        console.error('All fields are required');
        return;
    }
    const game = { name, type, imageUrl };

    fetch('/api/games', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(game)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        fetchGames(); // Refresh the list of games
        console.log('Game added:', data);
    })
    .catch(error => console.error('Error adding game:', error));
}

function deleteGame(id) {
    fetch(`/api/games/${id}`, {
        method: 'DELETE'
    })
    .then(() => {
        fetchGames();  // Refresh the list of games
        console.log('Game deleted');
    })
    .catch(error => console.error('Error deleting game:', error));
}

async function fetchUsers() {
    try {
        const response = await fetch('/api/users');
        const users = await response.json();
        const userNames = users.map(user => user.name);
        const userEmails = users.map(user => user.email.length);

        const ctx = document.getElementById('userChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: userNames,
                datasets: [{
                    label: '# of Characters in Email',
                    data: userEmails,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    } catch (error) {
        console.error('Error fetching users:', error);
    }
}

// Fetch stores from the API
async function fetchStores(url) {
    try {
        const response = await fetch(url);

        // Check if the response is OK (status 200-299)
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        console.log('Fetched stores:', data); // Log the fetched data for debugging
        displayStores(data);
    } catch (error) {
        console.error('Error fetching data:', error);
        const resultsDiv = document.getElementById('store-results');
        resultsDiv.innerHTML = `<p>Error fetching data: ${error.message}</p>`;
    }
}

// Fetch store by location
function findStoreByLocation() {
    const locationInput = document.getElementById('storeLocation'); // Correct ID
    if (!locationInput) {
        alert('Location input field is missing from the DOM');
        return;
    }

    const location = locationInput.value.trim();
    if (!location) {
        alert('Please enter a location');
        return;
    }

    const url = `/api/business/location?location=${encodeURIComponent(location)}`;
    fetchStores(url);
}

// Fetch store by name
function findStoreByName() {
    const nameInput = document.getElementById('storeName'); // Use the correct ID
    if (!nameInput) {
        alert('Name input field is missing from the DOM');
        return;
    }

    const name = nameInput.value.trim();
    if (!name) {
        alert('Please enter a name');
        return;
    }

    const url = `/api/business/name?name=${encodeURIComponent(name)}`;
    fetchStores(url);
}


// Fetch store by category
function findStoreByCategory() {
    const categoryInput = document.getElementById('storeCategory'); // Correct ID
    if (!categoryInput) {
        alert('Category input field is missing from the DOM');
        return;
    }

    const category = categoryInput.value.trim();
    if (!category) {
        alert('Please enter a category');
        return;
    }

    const url = `/api/business/category?category=${encodeURIComponent(category)}`;
    fetchStores(url);
}

// Fetch all stores
function getAllStores() {
    const url = '/api/business'; // API endpoint
    fetchStores(url);
}

// Display stores in the UI
function displayStores(stores) {
    const resultsDiv = document.getElementById('store-results');
    resultsDiv.innerHTML = '';

    // If stores is an array and contains data
    if (Array.isArray(stores) && stores.length > 0) {
        stores.forEach(store => {
            if (store.name && store.location && store.category) {
                const storeDiv = document.createElement('div');
                storeDiv.className = 'store';
                storeDiv.innerHTML = `
                    <h2>${store.name}</h2>
                    <img src="${store.imageUrl}" alt="$store.name"
                    style="width100px;height=100px;"/>
                    <p>Location: ${store.location}</p>
                    <p>Opening Times: ${store.time}</p>
                    <p>Category: ${store.category}</p>
                `;
                resultsDiv.appendChild(storeDiv);
            } else {
                console.error('Store data is missing required fields', store);
            }
        });
    } else if (stores && stores.name) {
        // If `stores` is a single store object
        const storeDiv = document.createElement('div');
        storeDiv.className = 'store';
        storeDiv.innerHTML = `
            <h2>${stores.name}</h2>
            <img src="${stores.imageUrl}" alt="$stores.name"
            style="width100px;height=100px;"/>
            <p>Location: ${stores.location}</p>
            <p>Opening Times: ${stores.time}</p>
            <p>Category: ${stores.category}</p>
        `;
        resultsDiv.appendChild(storeDiv);
    } else {
        // If no stores are found
        resultsDiv.innerHTML = '<p>No stores found</p>';
    }
}



// Function to create a new business profile
function createBusinessProfile() {
    const getInputValue = (id) => document.getElementById(id).value.trim();

    const name = getInputValue('businessName');
    const location = getInputValue('businessLocation');
    const category = getInputValue('businessCategory');
    const time = getInputValue('businessTime');
    const longitude = getInputValue('businessLongitude');
    const latitude = getInputValue('businessLatitude');
    const imageUrl = getInputValue('businessImageUrl');

    if (!name || !location || !category || !time || !longitude || !latitude) {
        console.error('All fields are required');
        alert('Please fill in all fields.');
        return;
    }

    const businessProfile = { name, location, category, time, longitude, latitude, imageUrl };

    console.log('Submitting business profile:', businessProfile);

    fetch('/api/business/addBusiness', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(businessProfile),
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json().catch(() => {
                throw new Error('Failed to parse JSON response');
            });
        })
        .then((data) => {
            document.getElementById('message').textContent = 'Business profile created successfully';
            document.getElementById('message').style.color = 'green';
            console.log('Business profile created:', data);
            document.getElementById('business-form').reset();
        })
        .catch((error) => {
            document.getElementById('message').textContent = 'Error creating business profile';
            document.getElementById('message').style.color = 'red';
            console.error('Error creating business profile:', error);
        });
}



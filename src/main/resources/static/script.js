/* script.js */

document.addEventListener('DOMContentLoaded', function() {
    fetchUsers();
    fetchGames();

    fetch('/store')
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

    fetch('/events')
        .then(response => response.json())
        .then(data => {
            const eventsContainer = document.querySelector('.event-hero');
            eventsContainer.innerHTML = data.map(event => `
                <div class="event-list">
                    <img src="${event.imageUrl}" alt="${event.title}" style="width:100px; height: 100px;"/>
                    <h3>${event.title}</h3>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error fetching events:', error));

    // Add event listener for the Create Business Profile form
    const createBusinessForm = document.getElementById('create-business-form');
    createBusinessForm.addEventListener('submit', function(event) {
        event.preventDefault();
        createBusinessProfile();
    });
});

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

// New store-related functions
async function fetchStores(url) {
    try {
        const response = await fetch(url);
        const data = await response.json();
        displayStores(data);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

function findStoreByLocation() {
    const location = document.getElementById('location').value;
    const url = `/businesses/location?location=${encodeURIComponent(location)}`;
    fetchStores(url);
}

function findStoreByName() {
    const name = document.getElementById('name').value;
    const url = `/businesses/name?name=${encodeURIComponent(name)}`;
    fetchStores(url);
}

function findStoreByCategory() {
    const category = document.getElementById('category').value;
    const url = `/businesses/category?category=${encodeURIComponent(category)}`;
    fetchStores(url);
}

function getAllStores() {
    const url = '/businesses';
    fetchStores(url);
}

function displayStores(stores) {
    const resultsDiv = document.getElementById('store-results');
    resultsDiv.innerHTML = '';
    if (Array.isArray(stores) && stores.length > 0) {
        stores.forEach(store => {
            const storeDiv = document.createElement('div');
            storeDiv.className = 'store';
            storeDiv.innerHTML = `
                <h2>${store.name}</h2>
                <p>Location: ${store.location}</p>
                <p>Category: ${store.category}</p>
            `;
            resultsDiv.appendChild(storeDiv);
        });
    } else if (stores) {
        const storeDiv = document.createElement('div');
        storeDiv.className = 'store';
        storeDiv.innerHTML = `
            <h2>${stores.name}</h2>
            <p>Location: ${stores.location}</p>
            <p>Category: ${stores.category}</p>
        `;
        resultsDiv.appendChild(storeDiv);
    } else {
        resultsDiv.innerHTML = '<p>No stores found</p>';
    }
}

// Function to create a new business profile
function createBusinessProfile() {
    const name = document.getElementById('businessName').value;
    const location = document.getElementById('businessLocation').value;
    const category = document.getElementById('businessCategory').value;

    if (!name || !location || !category) {
        console.error('All fields are required');
        return;
    }

    const businessProfile = { name, location, category };

    fetch('/businesses', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(businessProfile)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('message').textContent = 'Business profile created successfully';
        console.log('Business profile created:', data);
        // Optionally, clear the form fields
        document.getElementById('create-business-form').reset();
    })
    .catch(error => {
        document.getElementById('message').textContent = 'Error creating business profile';
        console.error('Error creating business profile:', error);
    });
}

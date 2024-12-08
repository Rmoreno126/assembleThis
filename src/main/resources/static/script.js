/* script.js */

document.addEventListener('DOMContentLoaded', function() {
    fetchUsers();
    fetchGames();
    fetchStoreItems();
    fetchEvents();

    // Add event listener for the Create Business Profile form
    const createBusinessForm = document.getElementById('create-business-form');
    createBusinessForm.addEventListener('submit', function (event) {
        event.preventDefault();
        createBusinessProfile();
    });
});

function fetchStores() {
    fetch('/api/business/all')
        .then(response => response.json())
        .then(data => {
            displayStores(data); // Use a function to display stores
        })
        .catch(error => console.error('Error fetching businesses:', error));
}

function fetchEvents() {
fetch('/api/events')
    .then(response => response.json())
    .then(data => {
        const eventsContainer = document.querySelector('.event-hero');
        eventsContainer.innerHTML = data.map(event => {
            // Handle missing or null fields
            const imageUrl = event.imageUrl || 'https://via.placeholder.com/100'; // Fallback image
            const name = event.name || 'Unnamed Event';
            const location = event.location || 'Location not available';

            return `
                <div class="event-list">
                    <img src="${imageUrl}" alt="${name}" style="width:100px; height: 100px;"/>
                    <h3>${name}</h3>
                    <p>${location}</p>
                </div>
            `;
        }).join('');
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

function createBusinessProfile() {
    const getInputValue = (id) => document.getElementById(id).value.trim();

    const name = getInputValue('businessName');
    const location = getInputValue('businessLocation');
    const category = getInputValue('businessCategory');
    const longitude = getInputValue('businessLongitude');
    const latitude = getInputValue('businessLatitude');
    const imageUrl = getInputValue('businessImageUrl');

    if (!name || !location || !category || !longitude || !latitude) {
        alert('Please fill in all required fields.');
        return;
    }

    const businessProfile = { name, location, category, longitude, latitude, imageUrl };

    fetch('/api/business/addBusiness', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(businessProfile),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            alert('Business profile created successfully');
            fetchStores(); // Refresh the list
        })
        .catch(error => console.error('Error creating business profile:', error));
}

function displayStores(stores) {
    const resultsDiv = document.getElementById('store-results');
    resultsDiv.innerHTML = '';

    if (stores.length > 0) {
        stores.forEach(store => {
            const storeDiv = document.createElement('div');
            storeDiv.className = 'store';
            storeDiv.innerHTML = `
                <h2>${store.name}</h2>
                <img src="${store.imageUrl}" alt="${store.name}" style="width:100px; height: 100px;"/>
                <p>Location: ${store.location}</p>
                <p>Category: ${store.category}</p>
            `;
            resultsDiv.appendChild(storeDiv);
        });
    } else {
        resultsDiv.innerHTML = '<p>No stores found</p>';
    }
}

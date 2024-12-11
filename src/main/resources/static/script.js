document.addEventListener('DOMContentLoaded', function () {
    // Fetch initial data
    fetchPagedStores(); // Fetch paged stores by default
    fetchGames();
    fetchEvents();

    // Add event listener for the Create Business Profile form
    const createBusinessForm = document.getElementById('business-form');
    if (createBusinessForm) {
        createBusinessForm.addEventListener('submit', function (event) {
            event.preventDefault();
            createBusinessProfile();
        });
    }

    // Add event listeners for pagination
    const prevPageButton = document.getElementById('prev-page');
    if (prevPageButton) {
        console.log('Attaching event listener to Previous Page button'); // Debugging
        prevPageButton.addEventListener('click', () => {
            console.log('Previous button clicked'); // Debugging
            if (currentPage > 0) {
                fetchPagedStores(currentPage - 1, pageSize);
            }
        });
    }

    const nextPageButton = document.getElementById('next-page');
    if (nextPageButton) {
        console.log('Next Page button found'); // Debugging
        nextPageButton.addEventListener('click', () => {
            console.log('Next button clicked'); // Debugging
            fetchPagedStores(currentPage + 1, pageSize);
        });
    } else {
        console.error('Next Page button not found'); // Debugging
    }

    // Event listeners for store search functionality
    const searchByLocationButton = document.getElementById('search-by-location');
    if (searchByLocationButton) {
        searchByLocationButton.addEventListener('click', findStoreByLocation);
    }

    const searchByNameButton = document.getElementById('search-by-name');
    if (searchByNameButton) {
        searchByNameButton.addEventListener('click', findStoreByName);
    }

    const searchByCategoryButton = document.getElementById('search-by-category');
    if (searchByCategoryButton) {
        searchByCategoryButton.addEventListener('click', findStoreByCategory);
    }

    const getAllStoresButton = document.getElementById('get-all-stores');
    if (getAllStoresButton) {
        getAllStoresButton.addEventListener('click', fetchStores);
    }

    // Add event listener for the Add Game button
    const addGameButton = document.getElementById('add-game-btn');
    if (addGameButton) {
        addGameButton.addEventListener('click', addGame);
    }

    // Map Initialization
    const map = L.map('map').setView([40.7994, -124.1644], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors',
    }).addTo(map);

    let marker;
    map.on('click', function (e) {
        const { lat, lng } = e.latlng;
        if (marker) {
            marker.setLatLng(e.latlng);
        } else {
            marker = L.marker(e.latlng).addTo(map);
        }

        document.getElementById('businessLatitude').value = lat;
        document.getElementById('businessLongitude').value = lng;

        fetch(`https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}`)
            .then(response => response.json())
            .then(data => {
                const address = data.display_name || 'Address not found';
                document.getElementById('businessLocation').value = address;
            })
            .catch(error => console.error('Error fetching address:', error));
    });
});

// Fetch and display stores
function fetchStores() {
    fetch('/api/business/all')
        .then(response => response.json())
        .then(data => displayStores(data))
        .catch(error => console.error('Error fetching businesses:', error));
}

// Display stores in the UI
function displayStores(stores) {
    const resultsDiv = document.querySelector('.business-list');
    if (!resultsDiv) {
        console.error('Error: .business-list container not found in the DOM.');
        return;
    }

    console.log('Rendering stores: ', stores); // Debugging
    resultsDiv.innerHTML = ''; // Clear previous content
    if (stores.length > 0) {
        stores.forEach(store => {
            const storeDiv = document.createElement('div');
            storeDiv.className = 'business-card';
            storeDiv.innerHTML = `
                <img src="${store.imageUrl || 'https://via.placeholder.com/300x200'}" alt="${store.name || 'Store Image'}" class="business-image">
                <h2>${store.name || 'Unnamed Store'}</h2>
                <p>${store.description || 'Description not available'}</p>
                <p>${store.operatingHoursSummary || 'Hours not available'}</p>
            `;
            resultsDiv.appendChild(storeDiv);
        });
    } else {
        resultsDiv.innerHTML = '<p>No businesses found</p>';
    }
}

let currentPage = 0;
const pageSize = 9;

function fetchPagedStores(page = 0, size = 9) {
    console.log(`Fetching page ${page} with size ${size}`); // Debugging
    fetch(`/api/business/paged?page=${page}&size=${size}`)
        .then(response => response.json())
        .then(data => {
            console.log('Fetched paged stores:', data);
            if (!data.content) {
                console.error('Invalid data structure:', data);
                return;
            }
            displayStores(data.content); // Render the stores
            updatePagination(data.page, data.totalPages); // Update pagination controls
        })
        .catch(error => console.error('Error fetching paged stores:', error));
}

function updatePagination(page, totalPages) {
    console.log(`Updating pagination: page ${page} of ${totalPages}`); // Debugging
    currentPage = page; // Ensure this updates

    const pageInfo = document.getElementById('page-info');
    if (pageInfo) pageInfo.textContent = `Page ${page + 1}`;

    const prevPageButton = document.getElementById('prev-page');
    if (prevPageButton) prevPageButton.disabled = page === 0;

    const nextPageButton = document.getElementById('next-page');
    if (nextPageButton) nextPageButton.disabled = page + 1 >= totalPages;
}

// Function to search stores by location
function findStoreByLocation() {
    const location = document.getElementById('storeLocation').value.trim();
    if (!location) {
        alert('Please enter a location.');
        return;
    }

    fetch(`/api/business/location?location=${encodeURIComponent(location)}`)
        .then(response => response.json())
        .then(data => {
            console.log('Stores by location:', data);
            displayStores(data);
        })
        .catch(error => console.error('Error searching stores by location:', error));
}

// Function to search stores by name
function findStoreByName() {
    const name = document.getElementById('storeName').value.trim();
    if (!name) {
        alert('Please enter a store name.');
        return;
    }

    fetch(`/api/business/name?name=${encodeURIComponent(name)}`)
        .then(response => response.json())
        .then(data => {
            console.log('Stores by name:', data);
            displayStores(data);
        })
        .catch(error => console.error('Error searching stores by name:', error));
}

// Function to search stores by category
function findStoreByCategory() {
    const category = document.getElementById('storeCategory').value.trim();
    if (!category) {
        alert('Please enter a category.');
        return;
    }

    fetch(`/api/business/category?category=${encodeURIComponent(category)}`)
        .then(response => response.json())
        .then(data => {
            console.log('Stores by category:', data);
            displayStores(data);
        })
        .catch(error => console.error('Error searching stores by category:', error));
}

// Function to get all stores
function getAllStores() {
    fetchStores(); // Reuses the existing function to fetch and display all stores
}

// Function for Operating Hours
function createOperatingHours() {
document.getElementById('business-form').addEventListener('submit', function() {
            const selectedDays = Array.from(document.querySelectorAll('input[name="businessDays"]:checked')).map(checkbox => checkbox.value);
            const businessStartTime = document.getElementById('businessStartTime').value;
            const businessEndTime = document.getElementById('businessEndTime').value;

            const openingHours = {
                days: selectedDays,
                startTime: businessStartTime,
                endTime: businessEndTime
            };

            console.log(openingHours);  // Log the opening hours for debugging

            // Add the opening hours to a hidden input field to include in form data
            const openingHoursInput = document.createElement('input');
            openingHoursInput.type = 'hidden';
            openingHoursInput.name = 'openingHours';
            openingHoursInput.value = JSON.stringify(openingHours);
            document.getElementById('business-form').appendChild(openingHoursInput);
        });
        }
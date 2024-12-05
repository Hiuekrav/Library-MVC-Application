document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');

    searchInput.addEventListener('input', function() {
        const title = searchInput.value;
        fetchBooks(title);
    });
});

function fetchBooks(title) {
    fetch(`books/search?title=${title}`)
        .then(response => response.json())
        .then(books => updateBookCards(books))
        .catch(error => console.error('Error:', error));
}

function updateBookCards(books) {
    const container = document.getElementById('bookCardsContainer');
    container.innerHTML = '';
    container.className= 'row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4';

    books.forEach(book => {
        const card = document.createElement('div');
        card.className = 'col';

        card.innerHTML = `
            <div class="card h-100">
                <div class="card-body">
                    <div class="row">
                        <div class="col-4"><strong>Title:</strong></div>
                        <div class="col-8">${book.title}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>Author:</strong></div>
                        <div class="col-8">${book.author}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>Number of pages:</strong></div>
                        <div class="col-8">${book.numberOfPages}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>Genre</strong></div>
                        <div class="col-8">${book.genre}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>Published date</strong></div>
                        <div class="col-8">${book.publishedDate}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>Rented</strong></div>
                        <div class="col-8">${book.rented}</div>
                    </div>
                    <div class="row">
                        <div class="col-4"><strong>Archived</strong></div>
                        <div class="col-8">${book.archive}</div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#rentModal" data-bs-book-id="${book.id}">Rent</button>
                        </div>
                        <div class="col-6">
                            <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#rentNowModal" data-bs-book-id="${book.id}">Rent now</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
        container.appendChild(card);
    });
}
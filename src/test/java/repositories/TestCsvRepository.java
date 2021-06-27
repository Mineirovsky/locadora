package repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contracts.drivers.IStorage;
import models.BaseModel;

class TestCsvRepository {
	private class Model extends BaseModel {}

	private CsvRepository<Model> repositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		IStorage storageMock = new IStorage() {
			
			@Override
			public boolean saveContents(String fileName, String contents) throws IOException {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public File file(String fileName) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean append(String fileName, String contents) throws IOException {
				// TODO Auto-generated method stub
				return false;
			}
		};

		repositoryMock = new CsvRepository<Model>(storageMock) {
			@Override
			public Collection<Model> all() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Model get(int id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Model save(Model entity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Model delete(Model entity) {
				// TODO Auto-generated method stub
				return null;
			}

			protected String getName() {
				return "mock";
			}
		};
	}

	@Test
	void testGetFileName() {
		assertEquals("mock.csv", repositoryMock.getFileName());
	}

}
